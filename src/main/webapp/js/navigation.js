function addNewItem(){
   
}

function registerStudent(){
    var mainWindow = document.getElementById("main-window") 
    var div = document.createElement("div")
    div.innerHTML = registerStudentTemplate;
          mainWindow.appendChild(div)   
}