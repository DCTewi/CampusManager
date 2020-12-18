"use strict";

const emailValidator = /^[\w\-._]{2,}@[\w\-_]{2,}\.[a-zA-Z]{2,}$/g;
const userNameValidator = /^.{3,30}$/g;
const phoneValidator = /^1[0-9]{10}$/g;
const passwordValidator = /^[a-zA-Z0-9_.!]{6,20}$/g;
const urlValidator = /^https?:\/\/.*$/g;
const captchaValidator = /[0-9]+/g;

function checkById(form, id, pattern) {
    const str = document.forms[form][id].value;
    const item = $("#" + id);

    console.log("Checking: " + str);

    if (str == null || str === "") {
        item.addClass("is-invalid")
        return false;
    }

    const flag = str.search(pattern);
    let ad = "is-invalid";
    let rm = "is-valid";
    if (flag === 0) {
        ad = [rm, rm = ad][0]
    }
    item.addClass(ad)
        .removeClass(rm);
    return (flag === 0);
}

function checkUserName(form, id) {
    return checkById(form, id, userNameValidator);
}

function checkEmail(form, id) {
    return checkById(form, id, emailValidator);
}

function checkPhone(form, id) {
    return checkById(form, id, phoneValidator);
}

function checkPassword(form, id) {
    return checkById(form, id, passwordValidator);
}

function checkUrl(form, id) {
    return checkById(form, id, urlValidator);
}

function checkCaptcha(form, id) {
    return checkById(form, id, captchaValidator);
}

function checkFormLogin() {
    return (
        checkEmail("loginForm", "loginEmail") &&
        checkPassword("loginForm", "loginPass") &&
        checkCaptcha("loginForm", "loginCaptcha"))
}

function checkFormRegister() {
    return (
        checkEmail("registerForm", "loginEmail") &&
        checkUserName("registerForm", "loginUsername") &&
        checkPassword("registerForm", "loginPass") &&
        checkPhone("registerForm", "loginPhone") &&
        checkCaptcha("registerForm", "loginCaptcha")
    )
}

function checkFormProfileAdmin() {
    const phone = $("#phone");
    const avatarURL = $("#avatarUrl");
    const constOk = (checkUserName("profileForm", "userName") &&
        checkPassword("profileForm", "password") &&
        (phone.val() === "" || checkPhone("profileForm", "phone")) &&
        (avatarURL.val() === "" || checkUrl("profileForm", "avatarUrl"))
    );
    if (phone.val() === "") phone.removeClass("is-valid").removeClass("is-invalid");
    if (avatarURL.val() === "") avatarURL.removeClass("is-valid").removeClass("is-invalid");

    const profileForm = document.forms["profileForm"];
    const newPassword = profileForm["newPassword"].value;
    const newPasswordConfirm = profileForm["newPasswordConfirm"].value;
    let passwordChangeOk = true;
    if (newPassword !== "") {
        passwordChangeOk = checkPassword("profileForm", "newPassword") &&
            checkPassword("profileForm", "newPasswordConfirm");
        if (!passwordChangeOk) return false;
        if (newPasswordConfirm !== newPassword) {
            $("#newPasswordConfirm").removeClass("is-valid")
                .addClass("is-invalid");
            return false;
        }
    } else {
        $("#newPassword").removeClass("is-valid")
            .removeClass("is-invalid");
        $("#newPasswordConfirm").removeClass("is-valid")
            .removeClass("is-invalid");
    }
    return constOk;
}

