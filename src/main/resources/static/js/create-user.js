let formButton = document.getElementById('formButton')


formButton.addEventListener('click', () => pushUser(
    document.getElementById('formUsername').value,
    document.getElementById('formPassword').value,
    document.getElementById('formName').value,
    document.getElementById('formLastname').value,
    document.getElementById('formAge').value,
    document.getElementById('formRoles'),
))

let fetchAllRoles = () => {
    fetch('http://localhost:3333/api/roles')
        .then(res => {
            return res.json()
        })
        .then(value => {
            let select = document.getElementById('formRoles')
            value.forEach( r => {
                let option = document.createElement("option")
                option.append(r.name)
                option.value = r.id
                select.appendChild(option)
            })
        })
}

let fetchPrincipal = () => {
    fetch('http://localhost:3333/api/principal')
        .then(res => {
            return res.json()
        })
        .then(value => {
            console.log(value)
            document.getElementById('headerUsername').append(value.name)
            value.authorities.forEach(a => {
                document.getElementById('headerRole').append(a.name + " ")
            })
        })
}

window.onblur = fetchPrincipal()
window.onload = fetchAllRoles()

let pushUser = (username, password, name, lastName, age, elem) => {
    let selectedRoles = []
    let roleOptions = elem && elem.options

    for (let i = 0; i < roleOptions.length; i++) {
        if(roleOptions[i].selected) {
            selectedRoles.push({ id: roleOptions[i].value, name: roleOptions[i].text})
        }
    }


    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            username: username,
            password: password,
            name: name,
            lastName: lastName,
            age: age,
            roles: selectedRoles
        })
    }

    fetch('http://localhost:3333/api/users', options)
        .then(res => {
            let popup = document.createElement("div")
            popup.style.position = "absolute"
            popup.style.display = "block"
            popup.style.padding = "20px 50px"
            popup.style.color = "white"
            popup.style.bottom = "5px"
            popup.style.right = "5px"
            popup.style.background = "green"
            popup.append("User added successfully")

            document.getElementById("body").appendChild(popup)

            setTimeout(() => {
                popup.style.display = "none"
            }, 5000)
        })


}

