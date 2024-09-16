const URL = "http://localhost:4545";

// set the modal menu element
let viewContactModal = document.getElementById("view_contact_modal");

// options with default values
const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    console.log("modal is shown");
  },
  onToggle: () => {
    console.log("modal has been toggled");
  },
};

// instance options object
const instanceOptions = {
  id: "viewContactModal ",
  override: true,
};

// modal object
let modal = new Modal(viewContactModal, options, instanceOptions);

// show the modal
function openContactModal() {
  modal.show();
}

// hide the modal
function closeContactModal() {
  modal.hide();
}

async function loadContactData(id) {
  console.log("id=" + id);

  try {
    const data = await (await fetch(`${URL}/api/contacts/${id}`)).json();

    document.querySelector("#contact_name").innerHTML = data.name;
    document.querySelector("#contact_email").innerHTML = data.email;
    document.querySelector("#contact_image").src = data.contactImageUrl;
  } catch (error) {
    console.log(error);
  }
  openContactModal();
}

// delete alert

function deleteContact(id) {
  Swal.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!",
  }).then((result) => {
    if (result.isConfirmed) {
      const url = `${URL}/user/contacts/delete/${id}`;
      window.location.replace(url);
      //   Swal.fire({
      //     title: "Deleted!",
      //     text: "Your contact has been deleted.",
      //     icon: "success",
      //   });
    }
  });
}
