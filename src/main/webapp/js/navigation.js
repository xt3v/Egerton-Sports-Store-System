addScript("js/templates/storekeeper/addnewitem.js")
let rate = ""
function addNewItem(){
   addToMain(addNewItemTemplate)
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