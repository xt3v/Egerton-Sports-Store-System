addScript("js/templates/storekeeper/addnewitem.js")
addScript("js/templates/storekeeper/registerstudent.js")

function addNewItem(){
   addToMain(addNewItemTemplate)
}


function registerStudent(){
	addToMain(registerStudentTemplate)
}



function addScript(path){
    var imported = document.createElement('script');
    imported.src = path;
    document.head.appendChild(imported);
}


function addToMain(template){
    var mainWindow = document.getElementById("main-window") 
    mainWindow.innerHTML = template
}