addScript("js/templates/storekeeper/addnewitem.js")
addScript("js/templates/registerstudent.js")
addScript("js/templates/storekeeper/clearstudent.js")
addScript("js/templates/storekeeper/returnitems.js")
addScript("js/templates/storekeeper/recoverlostitems.js")
addScript("js/templates/coordinator/registersport.js")
addScript("js/templates/coordinator/registerteam.js")
addScript("js/templates/coordinator/registercoach.js")
addScript("js/templates/coordinator/registerfield.js")
addScript("js/templates/storekeeper/issueitem.js")
addScript("js/templates/storekeeper/selectissueitems.js")

//storekeeper views
function clearStudent(){
	addToMain(clearStudentTemplate)
}

function recoverLostItems(){
    addToMain(recoverLostItemsTemplate)
    setupRecoverLostItemsTemplate()
}

function addNewItem(){
   addToMain(addNewItemTemplate)
   setupAddNewItemTemplate()
}

function issueItem(){
    addToMain(issueItemTemplate)
    setupIssueItemTemplate()
}
function returnItems(){
   addToMain(returnItemTemplate)
   setupReturnedItemsTemplate()
}

function selectIssueItems(){
    addToMain(selectIssueItemsTemplate)
    setupSelectIssueItemsTemplate()
}

//shared views
function registerStudent(){
    addToMain(registerStudentTemplate)
    setupRegisterStudentTemplate()
}

//coordinator views
function registerSport(){
    addToMain(registerSportTemplate)
    setupRegisterSportTemplate()
}

function registerTeam(){
    addToMain(registerTeamTemplate)
    setupRegisterTeamTemplate()
}

function registerCoach(){
    addToMain(registerCoachTemplate)
    setupRegisterCoachTemplate()
}

function registerField(){
    addToMain(registerFieldTemplate)
    setupRegisterFieldTemplate()
}


//helper functions
function addScript(path){
    var imported = document.createElement('script');
    imported.src = path;
    document.head.appendChild(imported);
}


function addToMain(template){
    var mainWindow = document.getElementById("main-window") 
    mainWindow.innerHTML = template
}



