<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});


Route::group(['prefix' => 'contact'], function() {
    Route::get('show', 'ContactController@show');
    Route::post('insert', 'ContactController@store');
    Route::delete('delete/{id}', 'ContactController@destroy');
    Route::post('update', 'ContactController@update');
});

Route::group(['prefix'=>'user'], function(){
  Route::post('/login', 'UserController@login_api');
});
