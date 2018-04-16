package com.lambun.gusser.feature.prebattle

import com.lambun.gusser.utils.FileUtils
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.http.HttpService
import java.io.File
import java.io.IOException


class PreBattlePresenter constructor(private var view: PreBattleContract.View) : PreBattleContract.Presenter {

    companion object {
        val PASSWORD = "1@3$5^"
    }

    private lateinit var web3j: Web3j

    override fun connectEth(ip: String) {
        // val rinkebyUrl = "https://rinkeby.infura.io/AjX9XNmwvVX9o8oXWqL8"
        val privateUrl = "http://$ip:8545"
        web3j = Web3jFactory.build(HttpService(privateUrl))
        launch(UI) {
            try {
                val web3jVersion = async(CommonPool) {
                    return@async web3j.web3ClientVersion().send().web3ClientVersion
                }.await()
                view.log("Connect success.")
                view.log("Web3j version: $web3jVersion")
            } catch (e: IOException) {
                e.message?.apply {
                    view.log(this)
                }
            }
        }
    }

    override fun getCredentials() {
        launch(UI) {
            try {
                if (FileUtils.isDirectoryEmpty(FileUtils.FILE_PATH_KEY)) {
                    val credentials = async(CommonPool) {
                        val key = WalletUtils.generateNewWalletFile(PASSWORD, File(FileUtils.FILE_PATH_KEY), false)
                        return@async WalletUtils.loadCredentials(PASSWORD, FileUtils.FILE_PATH_KEY + File.separator + key)
                    }.await()
                    view.showLocalCredentialsInfo(credentials)
                } else {
                    val file = File(FileUtils.FILE_PATH_KEY)
                    if (file.isDirectory) {
                        val files = file.listFiles()
                        if (files != null && files.isNotEmpty()) {
                            val credentials = async(CommonPool) {
                                return@async WalletUtils.loadCredentials(PASSWORD, files[0])
                            }.await()
                            view.showLocalCredentialsInfo(credentials)
                        }
                    }
                }
            } catch (e: Exception) {
                e.message?.apply {
                    view.log(this)
                }
            }
        }
    }

    override fun getRemoteUserInfo() {
    }

    override fun joinBattle() {
    }


}