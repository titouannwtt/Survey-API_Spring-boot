var btnConn = document.getElementById("btn_con");
var btnTest = document.getElementById("btn_tst");

//btnConn.addEventListener('click', login);
//btnTest.addEventListener('click', test);

function login(){
	console.log("Log Login");
	var user = document.getElementById("user").value;
	var pass = document.getElementById("pass").value;
	
	var request = new XMLHttpRequest();
	
	request.open('GET', 'https://apiweb.cactus-industries.fr/ProgWeb/LogIn?user='+user+'&pass='+pass, true)
	request.onload = function () {
		var data = JSON.parse(this.response)
		
		if (request.status >= 200 && request.status < 400){
			Document.cookie = 'token='+data['token']
		}
	}
}

function test(){
	var token = getCookie('token');
	var request = new XMLHttpRequest();
	
	request.open('GET', 'https://apiweb.cactus-industries.fr/ProgWeb/TestToken?token='+token, true)
	request.onload = function () {
		var data = JSON.parse(this.response);
		
		if (request.status >= 200 && request.status < 400){
			window.alert("Requête: "+data['status']);
		}
	}
}

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

