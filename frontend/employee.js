const backendUrl = "http://localhost:8090/deloitte-jax-rs-demo/employees";

function viewAllEmployees() {
  fetch(backendUrl)
    .then((response) => response.json())
    .then((data) => {
      let html =
        "<table class='table table-bordered'><tr><th>ID</th><th>Name</th><th>Salary</th></tr>";
      data.forEach((employee) => {
        html += `<tr><td>${employee.id}</td><td>${employee.name}</td><td>${employee.salary}</td></tr>`;
      });
      html += "</table>";
      document.getElementById("employeeList").innerHTML = html;
    })
    .catch((error) => console.error("Error:", error));
}

function addEmployee() {
  const name = document.getElementById("addName").value;
  const salary = document.getElementById("addSalary").value;

  const employee = {
    name: name,
    salary: salary,
  };

  fetch(backendUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(employee),
  })
    .then((response) => response.json())
    .then((data) => {
      document.getElementById("addResult").innerText =
        "Employee added successfully!";
      viewAllEmployees(); // Refresh the employee list
    })
    .catch((error) => console.error("Error:", error));
}

function updateEmployee() {
  const id = document.getElementById("updateId").value;
  const name = document.getElementById("updateName").value;
  const salary = document.getElementById("updateSalary").value;

  const employee = {
    id: id,
    name: name,
    salary: salary,
  };

  fetch(`${backendUrl}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(employee),
  })
    .then((response) => response.json())
    .then((data) => {
      document.getElementById("updateResult").innerText =
        "Employee updated successfully!";
      viewAllEmployees(); // Refresh the employee list
    })
    .catch((error) => console.error("Error:", error));
}

function deleteEmployee() {
  const id = document.getElementById("deleteId").value;

  fetch(`${backendUrl}/${id}`, {
    method: "DELETE",
  })
    .then((response) => {
      if (response.ok) {
        document.getElementById("deleteResult").innerText =
          "Employee deleted successfully!";
        viewAllEmployees(); // Refresh the employee list
      } else {
        document.getElementById("deleteResult").innerText =
          "Failed to delete employee.";
      }
    })
    .catch((error) => console.error("Error:", error));
}

