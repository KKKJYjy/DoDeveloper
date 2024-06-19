/**
 * 
 */


  function checkEmailFormat(emailAddress){
      let emailRegex = new RegExp('^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$');
      if(!emailRegex.test(emailAddress)){
    		return false;
      }
      return true;
  }

  function sendRequestToVerifyEmail(emailAddress){
    	
      let urlWhole = window.location.href;
      let urlPath = window.location.pathname;
      let urlWithoutPathName = urlWhole.substring(0, urlWhole.indexOf(urlPath));
	  
	  let urlInput = urlWithoutPathName + "/member/emailConfirmRequest";
	  
	  result = false;
	  
	  $.ajax({
          url: urlInput,
          type: "post",
          dataType: "json",
          data: {
              emailAddress: emailAddress,
            },
          success: function (data) {
        	  result = data;
          },
        });
	  
	  return result;
  }
  
  function sendEmailVerificationCode(code){
      let urlWhole = window.location.href;
      let urlPath = window.location.pathname;
      let urlWithoutPathName = urlWhole.substring(0, urlWhole.indexOf(urlPath));
	  
	  let urlInput = urlWithoutPathName + "/member/emailCode";
	  
	  result = false;
	  
	  $.ajax({
          url: urlInput,
          type: "post",
          dataType: "json",
          async: false,
          data: {
              code: code,
            },
          success: function (data) {
			if(data.isSuccess == "1"){
				result = true;
			}else{
				result = false;
			}
          },
        });
	  
	  return result;
  }