/**
 * 
 */

 const toggleShowPass = () => {
	 const ourPassFld = document.getElementById("upass");
	 if(ourPassFld.type === "password")
	 	ourPassFld.type = "text"
	 else
	 	ourPassFld.type="password"
	 
	 const userCPassField = document.getElementById("ucpass");
	 if(userCPassField !== null){
		 if(userCPassField.type === "password")
	 	userCPassField.type = "text"
	 else
	 	userCPassField.type="password"
	 }
 }
 
 