const baseUrl = "http://localhost:8081/deloitte-jax-rs-demo/departments"; // Base URL of your REST API

// Function to add a new department
async function addDepartment() {
    const deptName = document.getElementById('deptName').value;
    //const Id = document.getElementById('deptId').value;
    const location = document.getElementById('deptLocation').value;

    const department = {
        name: deptName,
        location: location
    };

    const response = await fetch(baseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(department)
    });

    if (response.ok) {
        alert('Department added successfully');
    } else {
        alert('Error adding department');
    }
}
function getAllDepartments() {
    fetch(baseUrl)
        .then(response => response.json())
        .then(data => {
            let deptListHTML = `
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>location</th>
                        </tr>
                    </thead>
                    <tbody>
            `;
            data.forEach(department => {
                deptListHTML += `
                    <tr>
                        <td>${department.id}</td>
                        <td>${department.name}</td>
                        <td>${department.location}</td>
                    </tr>
                `;
            });
            deptListHTML += '</tbody></table>';
            document.getElementById('deptList').innerHTML = deptListHTML;
            })
        .catch(error => console.error('Error:', error));
}


async function getDepartmentById() {
    const deptId = document.getElementById('getDeptId').value;

    const response = await fetch(`${baseUrl}/${deptId}`);

    if (response.ok) {
        const department = await response.json();
        document.getElementById('deptDetails').innerHTML = `
            <p>ID: ${department.id}</p>
            <p>Name: ${department.name}</p>
            <p>Manager ID: ${department.location}</p>
        `;
    } else {
        alert('Department not found');
    }
}

// Function to update department
async function updateDepartment() {
    const deptId = document.getElementById('updateDeptId').value;
    const newDeptName = document.getElementById('updateDeptName').value;
    const location = document.getElementById('updateLocation').value;

    const updatedDepartment = {
        departmentName: newDeptName,
        location: location
    };

    const response = await fetch(`${baseUrl}/${deptId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedDepartment)
    });

    if (response.ok) {
        alert('Department updated successfully');
    } else {
        alert('Error updating department');
    }
}

// Function to delete department
async function deleteDepartment() {
    const deptId = document.getElementById('deleteDeptId').value;

    const response = await fetch(`${baseUrl}/${deptId}`, {
        method: 'DELETE'
    });

    if (response.ok) {
        alert('Department deleted successfully');
    } else {
        alert('Error deleting department');
    }
}
