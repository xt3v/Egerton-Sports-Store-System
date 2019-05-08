addScript("js/templates/storekeeper/addnewitem.js")
addScript("js/templates/storekeeper/registerstudent.js")
addScript("js/templates/storekeeper/clearstudent.js")
addScript("js/templates/storekeeper/returnitems.js")
addScript("js/templates/storekeeper/recoverlostitems.js")

function addNewItem(){
   addToMain(addNewItemTemplate)
}
function recoverLostItems(){
    addToMain(recoverLostItemsTemplate)
    setupRecoverLostItemsTemplate()
}

function registerStudent(){
	addToMain(registerStudentTemplate)
}
function clearStudent(){
	addToMain(clearStudentTemplate)
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
