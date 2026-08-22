var a1=[1,2,3,4,5];
for(var i=0;i<a1.length;i++){
    console.log(a1[i]);
}

var students=[
    {name:"John",score:90},
    {name:"Mary",score:85},
    {name:"Janny",score:78}];

students.forEach(function(student,index){
    console.log("index:"+index+" "+student.name+" "+student.score);
});

a1.forEach(function(value,index){
    console.log("index:"+index+" "+value);
});