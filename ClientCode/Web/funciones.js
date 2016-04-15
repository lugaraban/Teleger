function registro(){
	var name=document.getElementById("nombre");
	var alias=document.getElementById("aliasR");
	var contrasena=document.getElementById("password");
	var imagen=document.getElementById("imagen");

	app.register(alias, contrasena, name, imagen);
}

function logIn(){
	/*var alias=document.getElementById("alias");
	var contrasena=document.getElementById("contrasena");

	app.LogIn(alias, contrasena);*/
	alert("Hello");
}