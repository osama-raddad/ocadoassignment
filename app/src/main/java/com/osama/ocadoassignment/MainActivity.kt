package com.osama.ocadoassignment

import android.app.ProgressDialog.show
import android.os.Bundle
import android.provider.Contacts
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.osama.domain.categorized_products.GetCategorizedProductsUseCaseImpl
import com.osama.domain.entity.CategorizedProduct
import com.osama.domain.entity.Transaction
import com.osama.ocadoassignment.ui.OcadoAssignmentTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        makeNetworkRequest()

        setContent {
            OcadoAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")

                }
            }
        }
    }

    @UiThread
    suspend fun makeNetworkRequest() {
        // slowFetch is another suspend function so instead of
        // blocking the main thread  makeNetworkRequest will `suspend` until the result is
        // ready
        val result = GetCategorizedProductsUseCaseImpl().invoke()
        // continue to execute after the result is ready
        show(result)
    }

    private fun show(result: Transaction<Map<String, List<CategorizedProduct>>?>) {
        Toast.makeText(this, "sada", Toast.LENGTH_LONG).show()
    }
}


@Composable
fun Greeting(name: String) {
    Column {
        for (i in 0..10) {
            Text(text = "Hello $name!$i")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OcadoAssignmentTheme {
        Greeting("Android")
    }
}