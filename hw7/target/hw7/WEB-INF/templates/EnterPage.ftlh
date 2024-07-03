<#import "commons.ftlh" as c>

<@c.page>
    <div class="enter form-box">
        <div class="header">Enter</div>
        <div class="body">
            <form class="enter form" method="post" action="">
                <input type="hidden" name="action" value="enter"/>
                <div class="field">
                    <div class="name">
                        <label for="login">Login</label>
                    </div>
                    <div class="value">
                        <input id="login" name="login"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="password">Password</label>
                    </div>
                    <div class="value">
                        <input id="password" name="password" type="password"/>
                    </div>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input type="submit" value="Enter">
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $(".enter form").submit(function () {
                const $form = $(this);
                const login = $form.find("input[name='login']").val();
                const password = $form.find("input[name='password']").val();
                const $error = $form.find(".error");
                const data = {
                        action: "enter",
                        login,
                        password
                    };

                ajax(data, $error);
                return false;
            })
        })
    </script>
</@c.page>
