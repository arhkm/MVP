<?php

namespace App\Http\Controllers;

use App\User;
use Auth;
use Illuminate\Http\Request;

class UserController extends Controller
{
    private $token;

    public function __construct()
    {
      $this->token = "sndjkasuilw37wy83whiw83yr387";
    }

    public function login_api(Request $request)
    {
      if (self::authorized($request->token)) {
        $auth = Auth::attempt(['email' => $request->email, 'password' => $request->password]);
        if ($auth) {
          $data = User::where('email',$email)->first();
          return $value = [
            'status'=>'success',
            'code'=>200,
            'data'=>[
              'email'=>$data->email,
              'nama'=>$data->nama
            ],
          ];
        }else {
          return $value = ['status'=>'failed','code'=>201];
        }
      }else {
        return $value = ['status'=> 'Token Required','code'=>403];
      }
    }

    public function authorized($token)
    {
      if ($token == null) {
        return false;
      }else{
        if ($token == $this->token) {
          return true;
        }else {
          return $value = ['status'=>'Token Required', 'code'=>403];
        }
      }
    }
}
