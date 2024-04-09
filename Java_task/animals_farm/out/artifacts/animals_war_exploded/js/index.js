const btnNew = document.getElementById("new");
const btnSave = document.getElementById("save");
const btnAdd = document.getElementById("add");
const btnCancel = document.getElementById("save-cancel");

const inputCommandName = document.getElementById("command-name");
const inputCommandText = document.getElementById("command-text");
const blockInputCommand = document.getElementById("new-cmd");
const mainBlockCommand = document.getElementById("main-block");
const selectExistingCmd = document.getElementById("existing-cmd");

const errorBlock = document.getElementById("error-block");


if (selectExistingCmd) {
    selectExistingCmd.addEventListener("change", (event) => {
        removeInfoMsg();
        btnAdd.disabled = false;
    });
}

if (btnNew) {
    btnNew.addEventListener("click", (event) => {
        removeInfoMsg();
        toggleStyleById(blockInputCommand, 'hide', '');
        toggleStyleById(mainBlockCommand, '', 'hide');
    });
}

if (btnCancel) {
    btnCancel.addEventListener("click", (event) => {
        setVisBlocks();
    });
}

if (btnSave) {
    btnSave.addEventListener("click", (event) => {
        event.preventDefault();
        handleFormSub()
    });
}

if (btnAdd) {
    btnAdd.addEventListener("click", (event) => {
        handleFormSubAdd()
    });
}

async function handleFormSubAdd() {
    let name = selectExistingCmd.value;
    if (name) {
        let optionCmd = document.querySelector(`#existing-cmd > option[data-name="${name}"]`);
        if (optionCmd) {
            let text = optionCmd.dataset.text;
            let animalId = getUrlParam('animalId');
            const data = new FormData();
            data.append('animalId', animalId);
            data.append('name', name);
            data.append('text', text);
            send(data).then(response => {
                setExistingCmdResp(animalId, response, optionCmd, name);
            });
        }
    }
}

function setExistingCmdResp(id, response, option, name) {
    btnAdd.disabled = true;
    let state = false;
    if (Array.isArray(response)) {
        state = showErrors(response);
    }
    if (state) return;
    option.remove();
    selectExistingCmd.value = "";
    selectCmd.insertAdjacentHTML("beforeend", option.outerHTML.toString());
    showInfoMessage(name);
}

function showInfoMessage(name) {
    let productCreate = `
    <div class="wrapper-msg pos-absolute wrap-text">
        <p id="text-msg" class="text-green text-center">command "${name}" was added successfully!</p>
    </div>`;
    inputCommand.parentNode.insertAdjacentHTML("afterend", productCreate);
}


function showErrMessage(errors) {
    let elCreate = `<div class="wrapper-msg wrap-text pos-absolute abs-top">`;
    errors.forEach(err => {
        elCreate += ` 
        <p class="text-center text-red">${err}</p>`;
    })
    elCreate += `</div>`;
    errorBlock.insertAdjacentHTML("beforeend", elCreate);
}


function showSingleErrMessage(error) {
    let elCreate = `
    <div class="wrapper-msg wrap-text pos-absolute abs-top">
        <p class="text-center text-red">${error}</p>
    </div>`;
    errorBlock.insertAdjacentHTML("beforeend", elCreate);
}

async function handleFormSub() {
    if (checkValidity(inputCommandName) && checkValidity(inputCommandText)) {
        let name = inputCommandName.value;
        let text = inputCommandText.value;
        let animalId = getUrlParam('animalId');
        const data = new FormData();
        data.append('animalId', animalId);
        data.append('name', name);
        data.append('text', text);
        send(data).then(response => {
            setNewCmdResp(response, name);
        });
    }
}

function checkValidity(item) {
    if (item.checkValidity() === false) {
        item.reportValidity();
        return false;
    }
    return true;
}


function setNewCmdResp(response, commandName) {
    errorBlock.replaceChildren();
    if ('error' in response) {
        showSingleErrMessage(response.error)
        return;
    }
    let state = false;
    if (Array.isArray(response)) {
        state = showErrors(response);
    }
    if (state) return;
    setVisBlocks();
    let command = response.animalsCommands.filter(el => el.name.toLowerCase() === commandName.toLowerCase());
    if (command.length) {
        let {name, text} = command[0];
        let optionCreate = `
    <option data-name="${name}" data-text="${text}" value="${name}">${name}</option>`;
        selectCmd.insertAdjacentHTML("beforeend", optionCreate);
        showInfoMessage(name);
    }
}

function showErrors(response) {
    let errIndex = response.indexOf("empty_field_err")
    if (errIndex !== -1) {
        response.splice(errIndex, 1);
        showErrMessage(response);
        return true;
    }
    return false;
}

async function send(data) {
        let response = await fetch('/farm/profile?animalId=' + data.get('animalId'), {
        method: 'POST',
        body: data,
    })
    return await response.json();
}


function toggleStyleById(item, paramOne, paramTwo) {
    if (paramOne) {
        item.classList.remove(paramOne);
    }
    if (paramTwo) {
        item.classList.add(paramTwo);
    }
}

function setVisBlocks() {
    toggleStyleById(blockInputCommand, '', 'hide');
    toggleStyleById(mainBlockCommand, 'hide', '');
    inputCommandName.value = "";
    inputCommandText.value = "";
}

const cmdInput = document.querySelectorAll(".cmd-input");

cmdInput.forEach((el) => {
    el.addEventListener("input", (event) => {
        btnSave.disabled = !(event.target.value).length;
    })
})

const inputCommand = document.getElementById("show-command");
const btnShowCommand = document.getElementById("btn-show");
const selectCmd = document.getElementById("list-cmd");

if (selectCmd) {
    selectCmd.addEventListener("change", (event) => {
        btnShowCommand.disabled = false;
        removeInfoMsg();
    });
}

function removeInfoMsg() {
    const message = document.getElementById("text-msg");
    if (message) {
        message.remove();
    }
}

if (btnShowCommand) {
    btnShowCommand.addEventListener("click", (event) => {
        let name = selectCmd.value;
        if (name) {
            let text = "Command not found";
            let optionCmd = document.querySelector(`#list-cmd > option[data-name="${name}"]`);
            if (optionCmd) {
                text = optionCmd.dataset.text;
            }
            inputCommand.value = text;
        }
    })
}

function getUrlParam(param) {
    const queryString = window.location.search;
    const urlParam = new URLSearchParams(queryString);
    return urlParam.get(param);
}

const paginationBtn = document.querySelectorAll(".pagination-btn");

paginationBtn.forEach((button) => {
    button.addEventListener("click", (event) => {
        let el = document.getElementById("animal_name");
        let animalName = getUrlParam('filter_by');
        if (el) {
            if (!!el.getAttribute('name') && !animalName) {
                el.setAttribute('name', "");
            }
        }
    })
})


