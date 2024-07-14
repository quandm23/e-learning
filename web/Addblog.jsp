<head>
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
    <script src="ckeditor/ckeditor.js" type="text/javascript"></script>
</head>


<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">

        <form action="addblog" method="POST" enctype="multipart/form-data">
            <div class="formbold-form-title">
                <h2 class="">ADD BLOG</h2>
                <p>
                    Insert all information for blog
                </p>
            </div>

            <div class="formbold-mb-3">
                <label for="address" class="formbold-form-label">
                    Image
                </label>
                <input required onchange="previewImage(event)"
                       type="file" name="image" accept="image/*" 
                       class="formbold-form-input"/>

                <img style="height: 10rem; width: 10rem; display: none" id="preview" alt="Image Preview" />
            </div>

            <div class="formbold-mb-3">
                <label for="address" class="formbold-form-label">
                    Title
                </label>
                <input
                    required
                    type="text"
                    name="title"
                    id="address"
                    class="formbold-form-input"
                    />
            </div>

            <div class="formbold-mb-3">
                <label for="address2" class="formbold-form-label">
                    Description
                </label>
                <input
                    required
                    type="text"
                    name="description"
                    id="address2"
                    class="formbold-form-input"
                    />
            </div>

            <div class="formbold-mb-3">
                <label for="address2" class="formbold-form-label">
                    Content
                </label>
                <textarea 
                    required
                    rows="10"
                    name="content" class="formbold-form-input">

                </textarea>
            </div>

            <div class="formbold-mb-3">
                <label for="address2" class="formbold-form-label">
                    Category
                </label>
                <select name="category" class="formbold-form-input">
                    <option value="education">Education</option>
                    <option value="technology">Technology</option>
                </select>
            </div>

            <button onclick="addedBlog()" class="formbold-btn">ADD</button>
            <a href="home">BACK TO HOME</a>
        </form>
    </div>
</div>

<script type="text/javascript">
    CKEDITOR.replace('content');


    function previewImage(event) {
        var file = event.target.files[0];
        if (file) {
            var reader = new FileReader();
            reader.onload = function (e) {
                var preview = document.getElementById('preview');
                preview.src = e.target.result;
                preview.style.display = 'block';
            };
            reader.readAsDataURL(file);
        }
    }
</script>

