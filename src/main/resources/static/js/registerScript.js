/**
 * 
 */




function validateForm() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    var dob = document.getElementById("date").value;
    var today = new Date();
    var dobDate = new Date(dob);
    var age = today.getFullYear() - dobDate.getFullYear();
    var monthDiff = today.getMonth() - dobDate.getMonth();

    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < dobDate.getDate())) {
        age--;
    }

    if (password != confirmPassword) {
        showToast("Passwords do not match.", "error");
        return false;
    }

    if (age < 12) {
        showToast("You must be at least 12 years old to register.", "error");
        return false;
    }

    if (!validatePassword(password)) {
        showToast("Invalid password. Password must contain at least 1 uppercase letter, 1 special character, and 1 number.", "invalid");
        return false;
    }

    return true;
}

function validatePassword(password) {
    var pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
    return pattern.test(password);
}

function showToast(msg, type) {
    var toastBox = document.getElementById('toastbox');
    var toast = document.createElement('div');
    toast.classList.add('toast');

    if (type === "error") {
        toast.classList.add('error');
        toast.innerHTML = '<i class="fa-solid fa-circle-xmark"></i> ' + msg;
    } else if (type === "invalid") {
        toast.classList.add('invalid');
        toast.innerHTML = '<i class="fa-solid fa-circle-exclamation"></i> ' + msg;
    } else if (type === "success") {
        toast.classList.add('success');
        toast.innerHTML = '<i class="fa-solid fa-circle-check"></i> ' + msg;
    }

    toastBox.appendChild(toast);

    setTimeout(function() {
        toast.remove();
    }, 6000);
}
