<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../Styles/IndexStyles.css">
    <link rel="stylesheet" href="../Styles/loginStyles.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
    <div class="main-cont app-font">
        <div>
            <h2>LogIn > </h2>
            <form action="../login-handle" class="login-form" method="post" >

                <!-- our Name field -->
                <!-- <div class="input-fields">
                    <label for="uname"> Name > </label>
                    <input type="text" id="uname" name="userName"  >
                </div> -->
                
                <!-- our Email field  -->
                <div class="input-fields">
                    <label for="uemail"> Email > </label>
                    <input type="email" id="uemail" name="userEmail" spellcheck="false" autocomplete="off" required >
                </div>

                <!-- our Phone Number  -->
                <!-- <div class="input-fields">
                    <label for="uphonenum"> Phone Number > </label>
                    <input type="text" id="uphonenum" name="userPhoneNumber" >
                </div> -->

                <div class="input-fields">
                    <label for="upass"> Password > </label>
                    <div style="position:relative;" >
                    	<input type="password" id="upass" name="userPass" spellcheck="false" autocomplete="off" required >
                   		<button class="our-eye-icon" type="button" onclick="toggleShowPass()" >
                    		<span class="material-symbols-outlined">
								visibility
							</span>
						</button>
                    </div>
                </div>

                <div >
                    <button class="btn-design app-font" type="submit" >
                    	LogIn!
                    </button>
                </div>

                <p class="register-p" >Not a User ?  <a href="./signup.jsp">Register!</a> </p>
                
            </form>
        </div>
    </div>
    <script type="text/javascript" src="../jsfiles/loginjs.js" ></script>
</body>
</html>