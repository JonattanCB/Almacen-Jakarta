function toggleSubmenu() {
    var submenu = document.getElementById("submenu-perfil");
    if (submenu) {
        if (submenu.classList.contains('visible')) {
            submenu.classList.remove('visible');
            setTimeout(function() {
                submenu.style.display = 'none';
            }, 500); // Tiempo de la transición (0.5s)
        } else {
            submenu.style.display = 'block';
            setTimeout(function() {
                submenu.classList.add('visible');
            }, 10); // Pequeño retraso para aplicar la transición
        }
    } else {
        console.error("Elemento 'submenu-perfil' no encontrado.");
    }
}

function toggleMenu() {
    var contenidoMenu = document.querySelector('.contenido-menu');
    var contenidoEscritorio = document.querySelector('.Contenido-escritorio-opciones');
    var visibleMenuDiv = document.querySelector('.visible-menu');
    if (contenidoMenu) {
        if (contenidoMenu.classList.contains('hidden')) {
            contenidoMenu.classList.remove('hidden');
        } else {
            contenidoMenu.classList.add('hidden');
        }
    } else {
        console.error("Elemento 'contenido-menu' no encontrado.");
    }

    if (contenidoEscritorio) {
        if (contenidoEscritorio.classList.contains('expanded')) {
            contenidoEscritorio.classList.remove('expanded');
        } else {
            contenidoEscritorio.classList.add('expanded');
        }
    } else {
        console.error("Elemento 'contenido-escritorio-opciones' no encontrado.");
    }

    if (visibleMenuDiv) {
        if (visibleMenuDiv.classList.contains('rotate')) {
            visibleMenuDiv.classList.remove('rotate');
        } else {
            visibleMenuDiv.classList.add('rotate');
        }
    } else {
        console.error("Elemento 'visible-menu' no encontrado.");
    }
}