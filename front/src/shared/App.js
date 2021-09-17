import React, {useEffect, useReducer, useState} from 'react';
import {Route, Switch} from 'react-router-dom';
import {Enroll, Inquire, Main, Notice} from '../Pages';
import axios from "axios";

function App() {
    const [isLogin , setIsLogin] = useState(false);
    const [loading , setLoading] = useState(false);

    useEffect(()=>{
        try{
            let data = {stdn: "", password:""};
            axios.post("" ,JSON.stringify(data), {
                headers: {
                    "Content-Type": `application/json`,
                }})
                .then(res =>{
                    console.log("res.data.accessToken : " + res.data);
                    axios.defaults.headers.common['Authorization'] = 'Bearer ' + res.data;
                    setIsLogin(true);

                })
                .catch(ex=>{
                    console.log("request fail : " + ex);
                })
                .finally(()=>{
                console.log("login request end");
                setLoading(true);
            });
        }catch(e){
            console.log(e);
        }
    },[]);

    function loginCallBack(login) {
        setIsLogin(login);
    }
    if(loading) {
        return (
            <div>
                <Route exact isLogin={isLogin} path="/" component={Main}/>
                <Switch>
                    <Route path="/Enroll/:name" component={Enroll}/>
                    <Route path="/Enroll" render={(props) => <Enroll {...props} loginCallBack={loginCallBack}/>}/>
                    <Route path="/Notice" component={Notice}/>
                    <Route path="/Inquire" component={Inquire}/>
                </Switch>
            </div>
        );
    }else {
        <div>loading...</div>
    }
}

export default App;