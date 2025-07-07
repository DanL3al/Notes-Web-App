let username = document.querySelector(".username");
let name = document.querySelector(".name");
let password = document.querySelector(".password");



async function register(){
    try{
        let response = await fetch("http://localhost:8888/auth/register", {
            method: "POST",
            headers: {
                'Content-Type':'application/json'
            },
            body:JSON.stringify({
                "username":username.value,
                "name":name.value,
                "password":password.value,
            })
        });

        if(!response.ok){
            throw new Error("Não foi possível cadastrar o usuário");
        }

        window.location.href = '/';

    }catch(error){
        console.error(error);
    }
}

async function logUser(){
    try{
        let response = await fetch("http://localhost:8888/auth/login", {
            method:"POST",
            headers : {
                'Content-Type':'application/json'
            },
            body : JSON.stringify({
                "username":username.value,
                "password":password.value
            })
        });
        if(!response.ok){
            throw new Error("Não foi possível logar o usuário");
        }

        const data = await response.json();
        document.cookie = `jwt=${data.token}; path=/; max-age=1800; SameSite=Lax`;
        await getPage(data.token);
    }
    catch(error){
        console.error(error);
    }
}

async function getPage(token){

    try{

        const response = await fetch("http://localhost:8888/notes",{
            method:'GET',
            headers:{
                'Authorization': `Bearer ${token}`
            }
        });

        if(!response.ok){
            throw new Error("Error getting your note pages")
        }

        window.location.href = '/notes'
    }catch (error) {
        console.log(error);
    }


}

function getTokenFromCookie() {
    const cookies = document.cookie.split(';');
    for (let cookie of cookies) {
        const [name, value] = cookie.trim().split('=');
        if (name === 'jwt') {
            return value;
        }
    }
    return null;
}

