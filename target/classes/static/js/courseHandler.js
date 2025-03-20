$(document).ready(function() {
    // When course is selected, fetch years
    $('#course').change(function() {
        let courseId = $(this).val();
        $('#year').empty().append('<option value="">Select Year</option>');
        $('#semester').empty().append('<option value="">Select Semester</option>');
        $('#subject').empty().append('<option value="">Select Subject</option>');

        if (courseId) {
            $.ajax({
                url: '/api/courses/' + courseId + '/years',
                type: 'GET',
                success: function(data) {
                    data.forEach(year => {
                        $('#year').append(`<option value="${year.id}">${year.yearNumber}</option>`);
                    });
                }
            });
        }
    });

    // When year is selected, fetch semesters
    $('#year').change(function() {
        let yearId = $(this).val();
        $('#semester').empty().append('<option value="">Select Semester</option>');
        $('#subject').empty().append('<option value="">Select Subject</option>');

        if (yearId) {
            $.ajax({
                url: '/api/years/' + yearId + '/semesters',
                type: 'GET',
                success: function(data) {
                    data.forEach(semester => {
                        $('#semester').append(`<option value="${semester.id}">${semester.semNumber}</option>`);
                    });
                }
            });
        }
    });

    // When semester is selected, fetch subjects
    $('#semester').change(function() {
        let semesterId = $(this).val();
        $('#subject').empty().append('<option value="">Select Subject</option>');

        if (semesterId) {
            $.ajax({
                url: '/api/semesters/' + semesterId + '/subjects',
                type: 'GET',
                success: function(data) {
                    data.forEach(subject => {
                        $('#subject').append(`<option value="${subject.id}">${subject.name}</option>`);
                    });
                }
            });
        }
    });

    // Handle form submission
    $('#courseForm').submit(function(event) {
        event.preventDefault();
        alert("Form Submitted Successfully!");
    });
});
