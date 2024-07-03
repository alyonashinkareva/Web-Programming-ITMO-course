<#import "commons.ftlh" as c>
<@c.page>
    <div class="article form-box">
        <div class="header">Article</div>
        <div class="body">
            <form class="article form" method="post" action="">
                <input type="hidden" name="action" value="makeArticle"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text"></textarea>
                    </div>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input type="submit" value="Create">
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $(".article form").submit(function () {
                // alert(1);
                const $form = $(this);
                const title = $form.find("input[name='title']").val();
                const text = $form.find("textarea[name='text']").val();
                const $error = $form.find(".error");
                const data = {
                    action: "makeArticle",
                    title,
                    text
                };
                // alert(data);
                ajax(data, $error);
                return false;
            })
        })
    </script>
</@c.page>