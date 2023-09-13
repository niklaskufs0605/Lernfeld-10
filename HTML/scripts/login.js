const loginButton = document.getElementById('loginButton');
const email = document.getElementById('email')
const password = document.getElementById('password');
const uuid_now = null;

loginButton.addEventListener('click', function(){
    login(email.value, password.value)
});

async function isUserValid(email, password){
    const url = 'http://localhost:8080/api/user/isUserValid';
    const data = {
        email: email,
        password: password
    };

    const resp = await fetch(url, {
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
        return response.json();
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    });
    return resp;
}

async function getUUIDfromUser(email){
    const url = 'http://localhost:8080/api/user/getUUIDbyEmail';
    const data = {
        email: email,
    };

    const resp = await fetch(url, {
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
        return response.json();
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    });
    return resp;
}



async function login(email, password){
    const userValid = await isUserValid(email, password);
    uuid_now = await getUUIDfromUser(email);
    if(userValid){
        console.log("Logged in");
    } else {
        console.log("not logged in");
    }
    
}