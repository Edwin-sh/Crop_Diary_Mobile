package com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components

import androidx.constraintlayout.compose.ConstraintSet
import com.myapps.cropdiarymobile.core.WindowGrid
import com.myapps.cropdiarymobile.core.WindowOrientation

internal fun loginConstraints(windowOrientation: WindowOrientation, grid: WindowGrid): ConstraintSet {
    return ConstraintSet {
        val title = createRefFor(LayoutId.title)
        val separator = createRefFor(LayoutId.separator)
        val socialMediaButtons = createRefFor(LayoutId.social_media_buttons)
        val emailInput = createRefFor(LayoutId.email_input)
        val passwordInput = createRefFor(LayoutId.password_input)
        val checkBox = createRefFor(LayoutId.check_box)
        val loginButton = createRefFor(LayoutId.login_button)
        val resetPasswordText = createRefFor(LayoutId.reset_password_text)
        val signUpText = createRefFor(LayoutId.sign_up_text)

        if (windowOrientation == WindowOrientation.Portrait) {
            constrain(title) {
                top.linkTo(parent.top, margin = grid.margin)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(socialMediaButtons) {
                top.linkTo(
                    parent.top,
                    margin = grid.height(1.5)
                )
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(separator) {
                top.linkTo(socialMediaButtons.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(emailInput.top)
            }
            constrain(emailInput) {
                top.linkTo(socialMediaButtons.bottom, margin = grid.height(1))
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(passwordInput) {
                top.linkTo(emailInput.bottom, margin = grid.margin)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(checkBox) {
                top.linkTo(passwordInput.bottom)
                end.linkTo(passwordInput.end)
            }
            constrain(loginButton) {
                top.linkTo(checkBox.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(resetPasswordText.top)
            }
            constrain(resetPasswordText) {
                top.linkTo(loginButton.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(
                    signUpText.bottom
                )
            }
            constrain(signUpText) {
                top.linkTo(resetPasswordText.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(
                    parent.bottom,
                    margin = grid.height(1)
                )
            }
        } else {
            constrain(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(socialMediaButtons) {
                top.linkTo(separator.top)
                start.linkTo(parent.start)
                bottom.linkTo(separator.bottom)
            }

            constrain(separator) {
                top.linkTo(parent.top, margin = grid.height(1))
                start.linkTo(socialMediaButtons.end)
                end.linkTo(emailInput.start)
                bottom.linkTo(parent.bottom)
            }
            constrain(emailInput) {
                top.linkTo(separator.top)
                start.linkTo(parent.start, margin = grid.width(2.5) + grid.minimumSpace)
                bottom.linkTo(passwordInput.top)
            }
            constrain(passwordInput) {
                top.linkTo(emailInput.bottom)
                start.linkTo(emailInput.start)
                end.linkTo(emailInput.end)
                bottom.linkTo(parent.bottom, margin = grid.margin)
            }
            constrain(checkBox) {
                top.linkTo(passwordInput.bottom)
                end.linkTo(passwordInput.end)
                //bottom.linkTo(parent.bottom)
            }
            constrain(resetPasswordText) {
                top.linkTo(separator.top)
                start.linkTo(emailInput.end)
                end.linkTo(parent.end)
                bottom.linkTo(signUpText.top)
            }
            constrain(signUpText) {
                top.linkTo(resetPasswordText.bottom)
                start.linkTo(resetPasswordText.start)
                end.linkTo(resetPasswordText.end)
                bottom.linkTo(loginButton.top)
            }
            constrain(loginButton) {
                //top.linkTo(signUpText.bottom)
                start.linkTo(signUpText.start)
                end.linkTo(signUpText.end)
                bottom.linkTo(parent.bottom)
            }

        }
    }
}