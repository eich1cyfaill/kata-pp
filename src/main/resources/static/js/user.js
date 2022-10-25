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
            document.getElementById('userId').append(value.principal.id)
            document.getElementById('userName').append(value.principal.name)
            document.getElementById('userSurname').append(value.principal.lastName)
            document.getElementById('userAge').append(value.principal.age)
            document.getElementById('userUsername').append(value.principal.username)
            value.principal.roles.forEach(r => {
                document.getElementById('userRole').append(r.name + " ")
            })

        })
}

window.onblur = fetchPrincipal()