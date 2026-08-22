a1=[1,2,3,4,5];
b1=a1;
b1.forEach(function(value,index){
    b1[index]=value*2;
});
console.log(a1);
console.log(b1);