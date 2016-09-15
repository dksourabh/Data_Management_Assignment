<!DOCTYPE html>
<html>
<head>
<script>
function validateForm(form) {
    var x = document.getElementById("id1").value;
     var y = document.getElementById("id2").value;
    if (x == null || x == "") {
        alert("Location must be filled out in 'City, State' form.");
        return false;
    }
    if (y == null || y == "") {
        alert("Disease name can't be empty");
        return false;
    }
}
function test(){
var x="default";
 x = document.getElementById("id1").value;
alert("I am testing, value of 1st textfield: "+x);
}
</script>
    <title>Medical web-service example</title>
</head>
<body bgcolor= "A17FF2" background="http://the420times.com/wp-content/uploads/2014/12/medical-wallpaper-11.jpg">
    
    <form action="hello" onsubmit="return validateForm(this)">
    
    <div align="center" style="color:#ccff66">
    <h1>Medical webservice example</h1>
        <label for="name">Enter your location:</label><br/>
        <input type="text" name="name" id="id1"/><br/>
        <label for="Disease">Enter your ailment:</label><br/>
        <input type="text" name="ailment" id="id2"/><br/>
        <input type="submit" value="submit"/>
        </div>
    </form>
</body>
</html>