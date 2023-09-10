const registerForm = document.getElementById('register');
const email = document.getElementById('email')
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirmPassword');
const registerButton = document.getElementById('registerButton');


function register(event){
    event.preventDefault();

    registerButton.addEventListener('click', function() {

        customValidity(email, password, confirmPassword);
        sendForm(email.value, password.value, confirmPassword.value);
    });

}

function checkPasswords(password, confirmPassword){
    if(password === confirmPassword){
        return true;
    } else{
        return false;
    }
}


async function isMailPresent(email) {
    const apiUrl = 'http://localhost:8080/api/user/getMails';
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error('API-Anfrage fehlgeschlagen');
        }
        const lowercaseEmail = email.toLowerCase();
        const emailList = await response.json();
        const lowercaseEmailList = emailList.map(mail => mail.toLowerCase());
        
        if(lowercaseEmailList.includes(lowercaseEmail)){
            return true;
        } else{
            return false;
        }
    } catch (error) {
        console.error(error);
    }
}


async function sendForm(email, password, confirmPassword){
    const isPresent = await isMailPresent(email);
    if(checkPasswords(password, confirmPassword) === true && isPresent === false){
        console.log("Formular kann gesendet werden");
    }else{
        console.log("Formular kann nicht gesendet werden");
    }
}

async function customValidity(emailInput, passwordInput, confirmPasswordInput){
    const isPresent = await isMailPresent(emailInput.value);

    if (checkPasswords(passwordInput.value, confirmPasswordInput.value)) {
        passwordInput.setCustomValidity('');
    } else{
        passwordInput.setCustomValidity('Passwörter stimmen nicht überein');
    }
    if(isPresent){
        emailInput.setCustomValidity('E-Mail wird bereits verwendet');
    } else{
        emailInput.setCustomValidity('');
    }
}
registerForm.addEventListener('submit', register);


