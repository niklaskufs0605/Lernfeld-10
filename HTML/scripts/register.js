const email = document.getElementById('email')
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirmPassword');
const registerButton = document.getElementById('registerButton');
const buttonTextElement = document.getElementById('buttonText');

registerButton.addEventListener('click', function() {
    customValidity(buttonTextElement);
    sendForm(email.value, password.value, confirmPassword.value);
});




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
        const url = 'http://localhost:8080/api/user/createUser';
    const data = {
        email: email,
        password: password
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        window.location.href = 'http://localhost:8000/login.html';
        return response.json();
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    });
    }else{
        console.log("Formular kann nicht gesendet werden");
    }

    


}

async function customValidity(){
    const mailPresent = await isMailPresent(email.value);
    const passwordsValid = checkPasswords(password.value, confirmPassword.value);
    console.log(passwordsValid);

    if (!passwordsValid) {
        buttonTextElement.textContent = "Passwörter stimmen nicht überein";
    } else{
        buttonTextElement.textContent = "";
    }
    if(mailPresent){
        buttonTextElement.textContent = "Email wird bereits verwendet";
    } else{
        buttonTextElement.textContent = "";
    }
}




