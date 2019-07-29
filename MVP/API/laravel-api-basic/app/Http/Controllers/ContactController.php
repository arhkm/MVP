<?php

namespace App\Http\Controllers;

use App\Contact;
use Illuminate\Http\Request;

class ContactController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        if($request->hasfile('photo')) 
        { 
            $file = $request->file('photo');
            $extension = $file->getClientOriginalExtension(); // getting image extension
            $filename =time().'.'.$extension;
            $file->move('img/profile', $filename);
        }else{
            $filename = "default_avatar.png";
        }

        

        $data = Contact::create([
                'nama' => request('nama'),
                'email' => request('email'),
                'phone' => request('phone'),
                'photo' => $filename,
            ]);

        if ($data) {
            $response = ['code'=>200,'status'=>'success'];
        }else{
            $response = ['code'=>201,'status'=>'error'];
        }

        return response()->json($response);
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Contact  $contact
     * @return \Illuminate\Http\Response
     */
    public function show(Contact $contact)
    {
        $show = Contact::all();
        return response()->json(['data'=>$show]);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Contact  $contact
     * @return \Illuminate\Http\Response
     */
    public function edit(Contact $contact)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Contact  $contact
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request)
    {
        $id = $request->id;
        $cek = Contact::where('id', $id)->count();

        if($cek){

            $old = Contact::where('id',$id)->first();

            if($request->hasfile('photo')) 
            { 
                $file = $request->file('photo');
                $extension = $file->getClientOriginalExtension(); // getting image extension
                $filename =time().'.'.$extension;
                $file->move('img/profile', $filename);

                if ($old->photo != "default_avatar.png") {
                    unlink('img/profile/'.$old->photo);
                }
            }else{
                $filename = $old->photo;
            } 

            
            $contact = Contact::where('id',$id)->update([
                'nama' => $request->nama,
                'email' => request('email'),
                'phone' => request('phone'),
                'photo' => $filename,
            ]);
          $response = ['status'=>'success','code'=>200];
        }else{
          $response = ['status'=>'error','code'=>201];
        }
        return response()->json($response);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Contact  $contact
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        // $id = $request->id;
        $cek = Contact::findOrFail($id);

        if($cek->delete()){
            $response = ['status'=>'success','code'=>200];
        }else{
            $response = ['status'=>'error','code'=>201];
        }
        return response()->json($response);
    }
}
