document.addEventListener('DOMContentLoaded', () => {
    const filters = document.querySelectorAll('.filters button');
    const searchBar = document.querySelector('.search-bar input');
    const searchButton = document.querySelector('.search-bar button');
    const courses = document.querySelectorAll('.course');

    // Add event listener to filters
    filters.forEach(filter => {
        filter.addEventListener('click', (e) => {
            filters.forEach(f => f.classList.remove('active'));
            e.target.classList.add('active');
            const filterText = e.target.textContent;
            filterCourses(filterText);
        });
    });

    // Add event listener to search button
    searchButton.addEventListener('click', () => {
        const query = searchBar.value.toLowerCase();
        searchCourses(query);
    });

    // Function to filter courses based on category
    function filterCourses(category) {
        courses.forEach(course => {
            if (category === 'All' || course.textContent.includes(category)) {
                course.style.display = 'block';
            } else {
                course.style.display = 'none';
            }
        });
    }

    // Function to search courses based on query
    function searchCourses(query) {
        courses.forEach(course => {
            if (course.textContent.toLowerCase().includes(query)) {
                course.style.display = 'block';
            } else {
                course.style.display = 'none';
            }
        });
    }
});
