$(document).ready(() => {
  $("#tabel-kursus").DataTable({
    ajax: {
      url: "/api/admin/course",
      dataSrc: "",
    },
    columnDefs: [
      {
        className: "text-center",
        targets: "_all",
        searchable: true,
        orderable: true,
      },
    ],
    order: [[1, "asc"]],
    columns: [
      { data: "id" },
      { data: "title" },
      { data: "mentor" },
      { data: "level" },
      { data: "price" },
      {
        data: null,
        render: (data) => {
          return /*html*/ `
                    <div class="flex m-auto gap-4 justify-center">
                        <button
                            type="button"
                            class="btn btn-primary btn-sm"
                            data-toggle="modal"
                            data-target="#details"
                            title="Details ${data.title}"
                            onclick="findById(${data.id})">
                            <span class="material-symbols-rounded"> info </span>
                        </button> 
                        <button
                            type="button"
                            class="btn btn-warning d-flex align-items-center"
                            data-bs-toggle="modal"
                            data-bs-target="#update"
                            title="Update ${data.title}"
                            onclick="beforeUpdateCourse(${data.id})">
                            <span class="material-symbols-rounded"> sync </span>
                        </button>
                        <button
                            type="button"
                            class="btn btn-danger d-flex align-items-center"
                            title="Delete ${data.title}"
                            onclick="deleteCourse(${data.id})">
                            <span class="material-symbols-rounded"> delete </span>
                        </button>   
                    </div>`;
        },
      },
    ],
  });
  $("#table-course").on("draw.dt", function () {
    $("#table-course")
      .DataTable()
      .column(0, { search: "applied", order: "applied" })
      .nodes()
      .each(function (cell, i) {
        cell.innerHTML = i + 1;
      });
  });
});


// Trigger Add Course Modal
const addCourseButton = document.getElementById("addCourseButton");
const addCourseModal = document.getElementById("addCourseModal");
const closeAddCourseModal = document.getElementById("closeAddCourseModal");
addCourseButton.addEventListener("click", function () {
  addCourseModal.classList.remove("hidden");
  addCourseModal.classList.add("bg-black/60");
});
closeAddCourseModal.addEventListener("click", function () {
	addCourseModal.classList.add("hidden");
	addCourseModal.classList.remove("bg-black/60");
});
