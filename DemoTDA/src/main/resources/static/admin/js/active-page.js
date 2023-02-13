const activePage= window.location.pathname;
console.log(activePage)

const navLinks= document.querySelectorAll(".nav-link").
forEach(link => {
	if(link.href.includes(activePage)){
		link.classList.add('active');
	}
})