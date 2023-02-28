const { create } = require('domain');
let fs=require('fs');
let v=require('http');
let s=v.createServer(function(req,res){//async
   res.setHeader('Content-Type','text/html');
   fs.readFile('./myhtml.html',function(err,data){
      res.write(data);
      console.log("oook");
      res.end();
   });
});
s.listen(8000,'localhost',function(){//async
    console.log("listening...")
});
console.log("okk..")