addScript("js/templates/addchildren.js")


function addNewItem(){
   
}

function registerStudent(){
    var mainWindow = document.getElementById("main-window") 
    var div = document.createElement("div")
    mainWindow.innerHTML = registerStudentTemplate
}


function addScript(path){
    var imported = document.createElement('script');
    imported.src = path;
    document.head.appendChild(imported);
}