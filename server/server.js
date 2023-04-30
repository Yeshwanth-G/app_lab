const express=require('express')
const cors=require('cors')
const app=express();
app.use(cors());
app.use(express.json())
app.use((req,res,next)=>{
    console.log("Req recieved..");
    next();
})
const {router}=require('./Routehandler')
app.use('/',router);
app.listen(8000,()=>console.log("Server Listening at 8000"));