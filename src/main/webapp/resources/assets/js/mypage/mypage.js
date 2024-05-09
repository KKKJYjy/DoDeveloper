$(function () {
  var staticBackdrop = document.getElementById("staticBackdrop");
  staticBackdrop.addEventListener("show.bs.modal", function (event) {
    console.log("staticBackdrop open");

    $("#currentUserPwd").val("");
    $("#userPwd").val("");
    $("#userPwdConfirm").val("");
  });

  $("#fileUploader").change(handleImgInput);

  $("#userPwd, #userPwdConfirm").on("keyup", function (e) {
    checkValidPassword();
  });

  getProfileImage();
});

function checkValidPassword() {
  // 비밀번호 : 4자 이상 12자 이하 필수 (비밀 번호 확인과 동일해야 함)

  let result = false;

  let pwd1 = $("#userPwd").val();
  let pwd2 = $("#userPwdConfirm").val();

  if (pwd1 == "" || pwd1.length < 1) {
    $("#errorMsgUserPwd").html("비밀번호는 필수 항목 입니다");
    $("#errorMsgUserPwdConfirm").html("");
    $("#errorMsgUserPwd").removeClass("text-danger");
    $("#errorMsgUserPwd").removeClass("text-success");
    $("#errorMsgUserPwd").addClass("text-primary");
  } else if (pwd1.length < 4 || pwd1.length > 13) {
    $("#errorMsgUserPwd").html("비밀번호는 4~12 글자로 입력하세요");
    if (pwd2 != "" && pwd1 != pwd2) {
      $("#errorMsgUserPwdConfirm").html("비밀번호가 서로 맞지 않습니다!");
      $("#errorMsgUserPwdConfirm").removeClass("text-primary");
      $("#errorMsgUserPwdConfirm").removeClass("text-success");
      $("#errorMsgUserPwdConfirm").addClass("text-danger");
      result = false;
    }
  } else if (pwd1.length >= 4 && pwd1.length <= 13) {
    if (pwd2 != "" && pwd1 != pwd2) {
      $("#errorMsgUserPwd").html("비밀번호가 서로 맞지 않습니다!");
      $("#errorMsgUserPwdConfirm").html("비밀번호가 서로 맞지 않습니다!");
      $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").removeClass(
        "text-primary"
      );
      $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").removeClass(
        "text-success"
      );
      $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").addClass("text-danger");

      result = false;
    } else if (pwd1 == pwd2) {
      $("#errorMsgUserPwd").html("비밀번호가 확인되었습니다.");
      $("#errorMsgUserPwdConfirm").html("비밀번호가 확인되었습니다.");
      $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").removeClass(
        "text-primary"
      );
      $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").removeClass("text-danger");
      $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").addClass("text-success");
      result = true;
    } else {
      if (pwd2 == "" && document.activeElement.id != "userPwd") {
        $("#errorMsgUserPwd").html("");
        $("#errorMsgUserPwdConfirm").html("비밀번호를 확인해주세요.");

        $("#errorMsgUserPwdConfirm").removeClass("text-danger");
        $("#errorMsgUserPwdConfirm").removeClass("text-success");
        $("#errorMsgUserPwdConfirm").addClass("text-primary");
      } else {
        $("#errorMsgUserPwd").html("");
        $("#errorMsgUserPwdConfirm").html("");
      }
      result = false;
    }
  }
  return result;
}

function validate() {
  let result = false;
  let isValidId = $("#checkUserId").val();

  if (
    isValidId &&
    checkValidId() &&
    checkValidPassword() &&
    checkValidName() &&
    checkValidEmail()
  ) {
    result = true;
  }
  console.log("result", result);
  return result;
}

const resizeImage = (settings) => {
  const file = settings.file;
  const maxSize = settings.maxSize;
  const reader = new FileReader();
  const image = new Image();
  const canvas = document.createElement("canvas");

  const dataURItoBlob = (dataURI) => {
    const bytes =
      dataURI.split(",")[0].indexOf("base64") >= 0
        ? atob(dataURI.split(",")[1])
        : unescape(dataURI.split(",")[1]);
    const mime = dataURI.split(",")[0].split(":")[1].split(";")[0];
    //console.log("mime", mime);
    const max = bytes.length;
    const ia = new Uint8Array(max);
    for (let i = 0; i < max; i++) ia[i] = bytes.charCodeAt(i);
    return new Blob([ia], { type: mime });
  };

  const resize = () => {
    let width = image.width;
    let height = image.height;
    if (width > height) {
      if (width > maxSize) {
        height *= maxSize / width;
        width = maxSize;
      }
    } else {
      if (height > maxSize) {
        width *= maxSize / height;
        height = maxSize;
      }
    }
    canvas.width = width;
    canvas.height = height;
    canvas.getContext("2d").drawImage(image, 0, 0, width, height);
    // console.log("file.type", file.type);
    const dataUrl = canvas.toDataURL(file.type);

    // 서버 저장
    // console.log("dataUrl", dataUrl.length);

    let prefix = dataUrl.split(",").shift();
    let base64 = dataUrl.substring(prefix.length + 1);
    // console.log("prefix", prefix);
    // console.log("base64", base64);
    setProfileImage(prefix, base64);

    return dataURItoBlob(dataUrl);
  };

  return new Promise((ok, no) => {
    if (!file) {
      return;
    }
    if (!file.type.match(/image.*/)) {
      no(new Error("Not an image"));
      return;
    }
    reader.onload = (readerEvent) => {
      image.onload = () => {
        return ok(resize());
      };
      image.src = readerEvent.target.result;
      // console.log("original base64", readerEvent.target.result);
    };
    reader.readAsDataURL(file);
  });
};

const handleImgInput = (e) => {
  const config = {
    file: e.target.files[0],
    maxSize: 350,
  };
  resizeImage(config)
    .then((resizedImage) => {
      // console.log("resizedImage", resizedImage);
      const url = window.URL.createObjectURL(resizedImage);
      //console.log("url", url);
      $("#profileImage").attr("src", url);

      /* blobToBase64(resizedImage).then(function(result) {
          console.log("resize blob to base64", result.length);
      }); */
    })
    .catch((err) => {
      console.log(err);
    });
};

function registerProfileImage() {
  $("#fileUploader").click();
}

function removeProfileImage() {
  let userId = "${loginMember.userId}";
  let data = {
    userId: userId,
  };

  $.ajax({
    url: "/mypage/removeProfileImage",
    type: "post",
    contentType: "application/json;charset=utf-8;",
    data: JSON.stringify(data),
    dataType: "json",
    success: function (data) {
      console.log(data);
      if (data.message == "Success") {
        $("#uploadBtn").show();
        $("#changeNDeleteBtns").removeClass("d-flex").addClass("d-none");
        $("#profileImage").attr(
          "src",
          "http://bootdey.com/img/Content/avatar/avatar1.png"
        );
      }
    },
  });
}

function setProfileImage(prefix, base64Encode) {
  //console.log("setProfileImage");

  let userId = "${loginMember.userId}";
  let data = {
    userId: userId,
    prefix: prefix,
    base64Encode: base64Encode,
  };

  $.ajax({
    url: "/mypage/setProfileImage",
    type: "post",
    contentType: "application/json;charset=utf-8;",
    data: JSON.stringify(data),
    dataType: "json",
    success: function (data) {
      // console.log(data);

      if (data.message == "Success") {
        $("#uploadBtn").hide();
        $("#changeNDeleteBtns").removeClass("d-none").addClass("d-flex");
      }
    },
  });
}

function getProfileImage() {
  // console.log("getProfileImage");

  let userId = "${loginMember.userId}";
  let data = {
    userId: userId,
  };

  $.ajax({
    url: "/mypage/getProfileImage",
    type: "post",
    contentType: "application/json;charset=utf-8;",
    data: JSON.stringify(data),
    dataType: "json",
    success: function (data) {
      //console.log("getProfileImage", data);

      if (data.message == "Success") {
        console.log("업로드 버튼 구성 변경 [사진 업로드 -> 변경/삭제]");
        // 업로드 버튼 구성 변경 [사진 업로드 -> 변경/삭제]
        $("#uploadBtn").hide();
        $("#changeNDeleteBtns").removeClass("d-none").addClass("d-flex");

        let base64Image = data.prefix + "," + data.base64Encode;
        //console.log("base64Image", base64Image);
        $("#profileImage").attr("src", base64Image);

        /* const max = data.base64Encode.length;
                const ia = new Uint8Array(max);
                for (let i = 0; i < max; i++) ia[i] = bytes.charCodeAt(i);
                const url = window.URL.createObjectURL(new Blob([ia], { type: mime }));
                //console.log("url", url);
                $("#profileImage").attr("src", url); */
      } else {
        console.log("업로드 버튼 show / 변경,삭제 버튼 hide");
        //
        $("#profileImage").attr(
          "src",
          "http://bootdey.com/img/Content/avatar/avatar1.png"
        );
        $("#uploadBtn").show();
        $("#changeNDeleteBtns").removeClass("d-flex").addClass("d-none");
      }
    },
  });
}
