let users = []
let roles = []
let tableRow = document.getElementById('usersHolder')
let editModal = document.getElementById('editModal')
let deleteModal = document.getElementById('deleteModal')
let editButton = document.getElementById('editButton')
let deleteButton = document.getElementById('deleteButton')


let fetchingGuys = () => {
    fetch('http://localhost:3333/api/users')
        .then(res => {
            return res.json()
        })
        .then(value => {
            users = value
            users.forEach(user => {
                let div = document.createElement("div")

                let name = document.createElement("p")
                name.classList.add("col")

                let id = document.createElement("p")
                id.classList.add("col")

                let lastName = document.createElement("p")
                lastName.classList.add("col")

                let age = document.createElement("p")
                age.classList.add("col")

                let username = document.createElement("p")
                username.classList.add("col")

                let role = document.createElement("p")
                role.classList.add("col")

                let editButtonContainer = document.createElement("div")
                editButtonContainer.classList.add("col")

                let deleteButtonContainer = document.createElement("div")
                deleteButtonContainer.classList.add("col")


                let editButton = document.createElement("button")
                editButton.classList.add("btn")
                editButton.classList.add("btn-info")
                editButton.style.outline = "none"
                editButton.setAttribute("data-toggle", "modal")
                editButton.setAttribute("data-target", "#editModal")
                editButton.style.background = "#17A2B8"
                editButton.append("Edit")
                editButton.addEventListener('click', () => fetchUserForModal(user.id))


                let deleteButton = document.createElement("button")
                deleteButton.classList.add("btn")
                deleteButton.classList.add("btn-danger")
                deleteButton.style.outline = "none"
                deleteButton.setAttribute("data-toggle", "modal")
                deleteButton.setAttribute("data-target", "#deleteModal")
                deleteButton.addEventListener('click', () => fetchUserForModal(user.id))
                deleteButton.append("Delete")



                name.append(user.name)
                id.append(user.id)
                lastName.append(user.lastName)
                age.append(user.age)
                username.append(user.username)
                editButtonContainer.append(editButton)
                deleteButtonContainer.append(deleteButton)

                user.roles.forEach(r => {
                    role.append(r.name + " ")
                })

                tableRow.style.margin = "20px 0"

                div.style.display = "flex"
                div.append(id, name, lastName, age, username, role, editButtonContainer, deleteButtonContainer)

                tableRow.appendChild(div)
            })
        })
}

let fetchUserForModal = (id) => {
    let user
    fetch('http://localhost:3333/api/users/' + id)
        .then(res => {
            return res.json()
        })
        .then(value => {
            user = value
            document.getElementById("editModalId").value = user.id
            document.getElementById("editModalUsername").value = user.username
            document.getElementById("editModalPassword").value = user.password
            document.getElementById("editModalName").value = user.name
            document.getElementById("editModalLastname").value = user.lastName
            document.getElementById("editModalAge").value = user.age

            document.getElementById("deleteModalId").value = user.id
            document.getElementById("deleteModalName").value = user.name
            document.getElementById("deleteModalLastname").value = user.lastName
            document.getElementById("deleteModalAge").value = user.age
        })




}

let fetchAllRoles = () => {
    fetch('http://localhost:3333/api/roles')
        .then(res => {
            return res.json()
        })
        .then(value => {
            let select = document.getElementById('editModalRoles')
            value.forEach( r => {
                let option = document.createElement("option")
                option.append(r.name)
                option.value = r.name
                select.appendChild(option)
            })
        })
}

let pushUserToEdit = () => {

    let selectedRoles = []
    let roleOptions = document.getElementById('editModalRoles') && document.getElementById('editModalRoles').options
    debugger

    for (let i = 0; i < roleOptions.length; i++) {
        debugger
        if(roleOptions[i].selected) {
            debugger
            selectedRoles.push(roleOptions[i].value || roleOptions[i].text)
        }
    }

    fetch("http://localhost:3333/api/users", {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            id: document.getElementById("editModalId").value,
            username: document.getElementById("editModalUsername").value,
            password: document.getElementById("editModalPassword").value,
            name: document.getElementById("editModalName").value,
            lastName: document.getElementById("editModalLastname").value,
            age: document.getElementById("editModalAge").value,
            roles: selectedRoles
        })
    }).then(r => {
        tableRow.replaceChildren(fetchingGuys())
    })
}

let pushUserToDelete = (id) => {
    fetch("/api/users/" + id, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    }).then(r => {
        tableRow.replaceChildren(fetchingGuys())
    })
}

editButton.addEventListener('click', () => {
    pushUserToEdit()
})

deleteButton.addEventListener('click', () => {
    pushUserToDelete(document.getElementById('deleteModalId').value)
})


window.onload = fetchAllRoles()
window.onload = fetchingGuys()

