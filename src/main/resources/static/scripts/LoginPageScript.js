document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('login-form');

    form.addEventListener('submit', function (event) {
        event.preventDefault();
        sendLoginData();
    });
});

function sendLoginData() {
    const usuario = document.getElementById('usuario').value;
    const password = document.getElementById('password').value;

    const data = {
        usuario: usuario,
        password: password,
    };

    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
        if (response.ok) {
            window.location.href = '/';
        } else {
            alert('Login falhou!');
        }
    })
        .catch(error => {
        console.error('Erro:', error);
    });
}
