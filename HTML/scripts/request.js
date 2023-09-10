function submitForm(event) {
    event.preventDefault();
    const apiUrl = 'http://localhost:8080/api/hello';

    // API-Anfrage senden
    fetch(apiUrl)
    .then(response => {
        if (!response.ok) {
        throw new Error('API-Anfrage fehlgeschlagen');
        }
        return response.json();
    })
    .then(data => {
        console.log(data)
        alert(data)
    })
    .catch(error => {
        alert(error)
    });
}

// Das Formular-Element abrufen
const meinFormular = document.getElementById('login');

// Das "submit" Ereignis dem Formular hinzufügen
meinFormular.addEventListener('submit', submitForm);