document.getElementById('btnTest').addEventListener('click', function() {
    fetch('http://localhost:8080/clientes') // Cambia "tu-endpoint" por el endpoint de tu Quarkus
        .then(response => response.json())
        .then(data => {
            document.getElementById('resultado').innerText = JSON.stringify(data);
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('resultado').innerText = 'Error al conectarse al backend';
        });
});

