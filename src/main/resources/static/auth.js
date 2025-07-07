let nameInitial = document.querySelector(".nameInitial");
let name = document.querySelector(".name");
const exitButton = document.querySelector(".exit");
const addNotesButton = document.querySelector(".addButton");
let id;


async function getPageInfo(){
    try{
        const response = await fetch("http://localhost:8888/users/info", {
            headers: {
                'Authorization': `Bearer ${getTokenFromCookie()}`
            }
        });
        if(!response.ok){
            throw new Error("Não foi possivel pegar as informações do usuário");
        }

        const data = await response.json();
        name.textContent = data.name;
        nameInitial.textContent = data.name.substring(0,1);


    }catch (error){
        console.log(error)
    }
}


async function createNote(){

    let textArea = document.querySelector(".textArea");

    try{
        const response = await fetch("http://localhost:8888/api/notes", {
            method:"POST",
            headers:{
                'Authorization': `Bearer ${getTokenFromCookie()}`,
                'Content-Type': 'application/json'
            },
            body:JSON.stringify({
                "message":textArea.value
            })
        });

        if(!response.ok){
            throw new Error("Failed at creating the Note");
        }

        getNotes();
        addModal();


    }catch(error){
        console.log(error);
    }
}


async function getNotes(){
    try {
        const response = await fetch("http://localhost:8888/api/notes", {
            headers: {
                'Authorization': `Bearer ${getTokenFromCookie()}`
            }
        });
        if(!response.ok){
            throw new Error("Failed at get notes");
        }

        const data = await response.json();
        createNoteHtml(data);


    }catch (error){
        console.log(error);
    }
}


function createNoteHtml(notes){

    let notesContainer = document.querySelector(".notesContainer");
    let noteHtml = '';

    for(let note in notes){
        noteHtml += `<div class="note">
                        <p class="noteMessage">${notes[note].message}</p>
                        <div class="note-buttons">
                            <button class="editButton" onclick="openEditModal(${notes[note].id})">Edit</button>
                            <button class="deleteButton" onclick="deleteNote(${notes[note].id})">Delete</button>
                        </div>     
                      </div>`;
    }
    notesContainer.innerHTML = noteHtml;
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


function addModal(){
    let modal = document.querySelector(".modalContainer");
    if(modal.style.display === "none" || modal.style.display === ""){
        modal.style.display = "flex";
    }else{
        modal.style.display = "none";
    }
}

async function putModal(){
    let textArea = document.querySelector(".editTextArea");
    try{
        const response = await fetch(`http://localhost:8888/api/notes/${id}`, {
            method: 'PUT',
            headers:{
                'Authorization': `Bearer ${getTokenFromCookie()}`,
                'Content-Type':'application/json'
            },
            body:JSON.stringify({
                "message": `${textArea.value}`
            })
        });

        if(!response.ok){
            throw new Error("Não foi possivel achar a nota");
        }
        getNotes();
        closeEditModal();
    }catch (error){
        console.log(error);
    }
}

async function deleteNote(noteId){
    try{
        const response = await fetch(`http://localhost:8888/api/notes/${noteId}`, {
            method:"DELETE",
            headers:{
                'Authorization': `Bearer ${getTokenFromCookie()}`
            }
        })

        if(!response.ok){
            throw new Error("Não foi possivel deletar a nota");
        }

        getNotes();

    }catch (error){
        console.log(error);
    }
}


function openEditModal(noteId){
    const modal = document.querySelector('.editModalContainer');
    modal.style.display = 'flex';
    id = noteId;
}

function closeEditModal() {
    document.querySelector('.editModalContainer').style.display = 'none';
}

function logoutUser(){
    document.cookie = "jwt=; path=/; max-age=0; SameSite=Lax";
    window.location.href = "/";
}



getPageInfo();
getNotes();
