<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Report Comment</title>
        <link rel="stylesheet" href="css/stylereportcomment.css">
    </head>

    <body>
        <div class="report-container">
            <h2>Report Comment</h2>
            <form id="reportComment" method="Post">
                <label for="reportedAccountInfo">Reported Account Information:</label>
                <input id="reportedAccountInfo" name="reportedAccountInfo" value="${commentlesson.getFullname()}" readonly/>

                <label for="report_description">Report Description:</label>
                <input id="report_description" name="report_description" value="${commentlesson.getComment()}" readonly/>

                <label for="report_type">Report Type:</label>
                <select id="report_type" name="report_type" required onchange="toggleOtherInput()">
                    <option value="spam">Spam</option>
                    <option value="abusive">Abusive Content</option>
                    <option value="other">Other</option>
                </select>

                <div id="otherTypeContainer" style="display: none;">
                    <label for="other_type">Please specify:</label>
                    <input type="text" id="other_type" name="other_type" placeholder="Specify other report type">
                    <div name="errreportcomment"  class="error" id="errreportcomment"></div>
                </div>

                <input type="text" id="account_id" name="account_id" class="hidden" value="${commentlesson.getAccount_id()}">
                <input type="text" id="comment_id" name="comment_id" class="hidden" value="${comment_id}">
                <input type="text" id="subject_id" name="subject_id" class="hidden" value="${subject_id}">
                <input type="text" id="course_id" name="course_id" class="hidden" value="${course_id}">
                <input type="text" id="chapter_id" name="chapter_id" class="hidden" value="${chapter_id}">
                <input type="text" id="lesson_id" name="lesson_id" class="hidden" value="${lesson_id}">

                <button type="submit" id="submitreport"  onclick="validate(event)">Submit Report</button>
            </form>
        </div>

        <script>
            function toggleOtherInput() {
                var report_type = document.getElementById('report_type').value;
                var otherTypeContainer = document.getElementById('otherTypeContainer');
                if (report_type === 'other') {
                    otherTypeContainer.style.display = 'block';
                } else {
                    otherTypeContainer.style.display = 'none';
                }
            }

            function validate(event) {
                const submitreport = document.querySelector("#submitreport").value.trim();
                const report_type = document.querySelector("#report_type").value;
                if (report_type === "other") {
                    const errreportcomment = document.querySelector("#errreportcomment");
                    const other_type = document.querySelector("#other_type").value.trim();
                    if (other_type.length < 1 || other_type.length > 255) {
                        errreportcomment.innerHTML = 'Report comment can be between 0 and 255 characters long, should provide specific and detailed information about the issue being reported.';
                        event.preventDefault();
                    } else {
                        let reportComment = document.querySelector("#reportComment");
                        errreportcomment.innerHTML = "";
                        reportComment.submit();
                    }
                } else {
                    let reportComment = document.querySelector("#reportComment");
                    reportComment.submit();
                }
            }
        </script>
    </body>
</html>
