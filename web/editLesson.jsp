<%-- 
    Document   : editLesson
    Created on : Jun 4, 2024, 2:56:59 PM
    Author     : ThaiGay
--%>

<style>
    @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    body {
        font-family: 'Inter', sans-serif;
    }
    .formbold-mb-3 {
        margin-bottom: 15px;
    }
    .formbold-relative {
        position: relative;
    }
    .formbold-opacity-0 {
        opacity: 0;
    }
    .formbold-stroke-current {
        stroke: currentColor;
    }
    #supportCheckbox:checked ~ div span {
        opacity: 1;
    }

    .formbold-main-wrapper {
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 48px;
    }

    .formbold-form-wrapper {
        margin: 0 auto;
        max-width: 570px;
        width: 100%;
        background: white;
        padding: 40px;
    }

    .formbold-img {
        margin-bottom: 45px;
    }

    .formbold-form-title {
        margin-bottom: 30px;
    }
    .formbold-form-title h2 {
        font-weight: 600;
        font-size: 28px;
        line-height: 34px;
        color: #07074d;
    }
    .formbold-form-title p {
        font-size: 16px;
        line-height: 24px;
        color: #536387;
        margin-top: 12px;
    }

    .formbold-input-flex {
        display: flex;
        gap: 20px;
        margin-bottom: 15px;
    }
    .formbold-input-flex > div {
        width: 50%;
    }
    .formbold-form-input {
        width: 100%;
        padding: 13px 22px;
        border-radius: 5px;
        border: 1px solid #dde3ec;
        background: #ffffff;
        font-weight: 500;
        font-size: 16px;
        color: #536387;
        outline: none;
        resize: none;
    }
    .formbold-form-input:focus {
        border-color: #6a64f1;
        box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
    }
    .formbold-form-label {
        color: #536387;
        font-size: 14px;
        line-height: 24px;
        display: block;
        margin-bottom: 10px;
    }

    .formbold-checkbox-label {
        display: flex;
        cursor: pointer;
        user-select: none;
        font-size: 16px;
        line-height: 24px;
        color: #536387;
    }
    .formbold-checkbox-label a {
        margin-left: 5px;
        color: #6a64f1;
    }
    .formbold-input-checkbox {
        position: absolute;
        width: 1px;
        height: 1px;
        padding: 0;
        margin: -1px;
        overflow: hidden;
        clip: rect(0, 0, 0, 0);
        white-space: nowrap;
        border-width: 0;
    }
    .formbold-checkbox-inner {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 20px;
        height: 20px;
        margin-right: 16px;
        margin-top: 2px;
        border: 0.7px solid #dde3ec;
        border-radius: 3px;
    }

    .formbold-btn {
        font-size: 16px;
        border-radius: 5px;
        padding: 14px 25px;
        border: none;
        font-weight: 500;
        background-color: #6a64f1;
        color: white;
        cursor: pointer;
        margin-top: 25px;
    }
    .formbold-btn:hover {
        box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
    }
</style>


<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">

        <form id="editLessonForm" action="editLesson" method="POST">
            <input type="hidden" name="lessonid" value="${lesson.lesson_id}"/>
            <div class="formbold-form-title">
                <h2 class="">EDIT LESSON</h2>
                <p>
                    Update information for lesson
                </p>
                <h1 style="color: green;">${mess}</h1>
            </div>
            <input type="hidden" name="chapterid" value="${chapterid}"/>
            <div class="formbold-mb-3">
                <label class="formbold-form-label">
                    Lesson NO.
                </label>
                <input
                    readonly
                    value="${lesson.lesson_no}"
                    type="text"
                    name="lesson_no"
                    class="formbold-form-input"
                    />
            </div>

            <div class="formbold-mb-3">
                <label class="formbold-form-label">
                    Lesson Name
                </label>
                <input
                    required
                    value="${lesson.lesson_name}"
                    type="text"
                    name="lesson_name"
                    class="formbold-form-input"
                    />
            </div>

            <div class="formbold-mb-3">
                <label class="formbold-form-label">
                    Link video
                </label>
                <input
                    value="${lesson.video}"
                    id="video"
                    type="text"
                    name="video"
                    class="formbold-form-input"
                    />
            </div>
            <div class="formbold-mb-3">
                <label class="formbold-form-label">
                    Document
                </label>
                <textarea 
                    id="doc"
                    rows="5"
                    name="document"
                    class="formbold-form-input"> ${lesson.document}

                </textarea>
            </div>
            <div class="formbold-mb-3">
                <label class="formbold-form-label">
                    Description
                </label>
                <input
                    value="${lesson.description}"
                    required
                    type="text"
                    name="description"
                    class="formbold-form-input"
                    />
            </div>

            <button class="formbold-btn">Edit</button>
            <a href="manageLesson?chapterid=${lesson.chapter_id}">BACK TO LESSON LIST</a>
        </form>
    </div>
</div>

<script>
    // Xu lý - lecturer không duoc bo trong dong thoi Video và Doc,bat buoc phai dien vao 1 trong 2 hoac ca 2.
    document.getElementById('editLessonForm').addEventListener('submit', function(event){
        var video = document.getElementById('video').value;
        var doc = document.getElementById('doc').value;
        if(video.trim() === '' && doc.trim() === ''){
            event.preventDefault();
            alert('You must input Video or Document !');
        }
    });
</script>
