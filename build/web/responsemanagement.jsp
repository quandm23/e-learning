<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Response Management</title>
        <link rel="stylesheet" href="css/stylesresponsemanagement.css">
        <style>
            .message {
                color: green;
                font-size: 25px;
                margin-top: 5px;
            }
            .error {
                color: red;
                font-size: 12px;
                margin-top: 5px;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <button class="header-button"><a href="#">Back</a></button>
            <button class="header-button active" id="unrepliedBtn" onclick="showUnreplied()">Unreplied</button>
            <button class="header-button" id="repliedBtn" onclick="showReplied()">Replied</button>
            <button class="header-button" onclick="showRatings()">Rate</button>
        </div>
        <div class="container">
            <h3 class="message">${message}</h3>
            <h2>Comments</h2>
            <div id="unrepliedComments" class="comments-section">
                <table>
                    <thead>
                        <tr>
                            <th>Learner</th>
                            <th>Comment</th>
                            <th>Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${datacommentLessonUnreplied}" var="comment">
                            <tr>
                                <td>${comment.getFullname()}</td>
                                <td>${comment.getComment()}</td>
                                <td>${comment.getComment_date()}</td>
                                <td><button class="reply" onclick="replyComment(${comment.getComment_id()}, ${comment.getLesson_id()}, ${subject_id})">Reply</button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${noOfPages > 1}">
                    <div class="pagination">
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <a href="responemanagement?subject_id=${subject_id}&page=${i}" class="${page == i ? 'active' : ''}">${i}</a>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
            <div id="repliedComments" class="comments-section hidden">
                <table>
                    <thead>
                        <tr>
                            <th>Learner</th>
                            <th>Comment</th>
                            <th>Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Learner3</td>
                            <td>Thank you!</td>
                            <td>2024-07-12</td>
                            <td>Replied</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="ratingsSection" class="comments-section hidden">
                <table>
                    <thead>
                        <tr>
                            <th>Learner</th>
                            <th>Stars</th>
                            <th>Rating</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Learner1</td>
                            <td>5</td>
                            <td>Very helpful course!</td>
                            <td>2024-07-10</td>
                        </tr>
                        <tr>
                            <td>Learner2</td>
                            <td>4</td>
                            <td>Good course, but needs more examples.</td>
                            <td>2024-07-11</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <form action="addcommentlesson" method="Post">
                <div id="replyBox" class="reply-box hidden">
                    <input type="hidden" name="comment_id" id="comment_id" value=""/>
                    <input type="hidden" name="lesson_id" id="lesson_id" value=""/>
                    <input type="hidden" name="subject_id" id="subject_id" value=""/>
                    <textarea placeholder="Enter your reply..." name="comment" id="comment"></textarea>
                    <div name="err" id="err" class="error"></div>
                    <button type="submit" class="send-reply" type="button" id="submit">Send</button>
                    <button class="cancel-reply" type="button" id="cancel">Cancel</button>
                </div>
            </form>
        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const cancel = document.getElementById("cancel");
                cancel.addEventListener("click", () => {
                    let err = document.querySelector("#err");
                    err.innerHTML = "";
                    document.querySelector("#comment").value = "";
                    var replyBox = document.getElementById('replyBox');
                    replyBox.classList.add('hidden');
                    let comment_id = document.getElementById("comment_id");
                    comment_id.value = "";
                });

                const submit = document.getElementById("submit");
                submit.addEventListener("click", (event) => {
                    let reply = document.querySelector("#comment").value.trim();
                    let err = document.querySelector("#err");

                    if (reply.length < 1 || reply.length > 1000) {
                        err.innerHTML = "Reply must be between 1 and 1000 alphanumeric characters.";
                        event.preventDefault();
                    } else {
                        err.innerHTML = "";
                    }
                });

            });

            function showUnreplied() {
                document.getElementById('unrepliedBtn').classList.add('active');
                document.getElementById('repliedBtn').classList.remove('active');
                document.getElementById('unrepliedComments').classList.remove('hidden');
                document.getElementById('repliedComments').classList.add('hidden');
                document.getElementById('ratingsSection').classList.add('hidden');
            }

            function showReplied() {
                document.getElementById('unrepliedBtn').classList.remove('active');
                document.getElementById('repliedBtn').classList.add('active');
                document.getElementById('unrepliedComments').classList.add('hidden');
                document.getElementById('repliedComments').classList.remove('hidden');
                document.getElementById('ratingsSection').classList.add('hidden');
            }

            function showRatings() {
                document.getElementById('unrepliedBtn').classList.remove('active');
                document.getElementById('repliedBtn').classList.remove('active');
                document.getElementById('unrepliedComments').classList.add('hidden');
                document.getElementById('repliedComments').classList.add('hidden');
                document.getElementById('ratingsSection').classList.remove('hidden');
            }


            function replyComment(comment_id, lesson_id, subject_id) {
                var replyBox = document.getElementById('replyBox');
                replyBox.classList.remove('hidden');
                let comment_id_input = document.querySelector("#comment_id");
                comment_id_input.value = comment_id;
                let lesson_id_input = document.querySelector("#lesson_id");
                lesson_id_input.value = lesson_id;
                let subject_id_input = document.querySelector("#subject_id");
                subject_id_input.value = subject_id;
                console.log(comment_id_input);
                console.log(lesson_id_input);
                console.log(subject_id_input);
                // Disable other reply buttons
            }
        </script>
    </body>
</html>
