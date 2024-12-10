package com.api;

import com.api.listeners.*;
import com.api.utils.reporter.*;
import com.api.models.BaseSetup;
import com.api.utils.TestConfig;

import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.api.utils.Logger.getLogger;

import java.io.File;
import java.io.IOException;


@Listeners( { ExtentReportListener.class, AnnotationTransformer.class } )

public class TestBase {

    public static BaseSetup baseSetup = new BaseSetup();
    public static TestConfig testConfig = ConfigFactory.create(TestConfig.class);

    @SneakyThrows
    @BeforeSuite(alwaysRun = true)
    @Parameters({"env"})
    public void initialSetUp(@Optional("stage") String env) throws ParseException, IOException {
        getLogger().info("---------------- Test Suite setUp started -----------");
       
        baseSetup.setEnvironment(env);
        baseSetup.setAppApiUrl(getAppApiUrlForEnv(ENV.getEnvByValue(env)));
        getLogger().info(baseSetup.toString());

//		setting Up credentials
		/*
		 * if (testConfig.credentialsFromLocalFile()) {
		 * SetCredentialsForSuite.setCredentialsFromLocalFile(); } else {
		 * SetCredentialsForSuite.setCredentialsFromAws(); }
		 */


        //        creating filed download directory if not present.
        File downloadFolder = new File(FrameworkConstants.FIlE_DOWNLOAD_PATH);
        if (downloadFolder.exists()) {
            FileUtils.forceDelete(downloadFolder);
        }
        downloadFolder.mkdirs();
        getLogger().info("browser download directory set to " + FrameworkConstants.FIlE_DOWNLOAD_PATH);

    }



    public synchronized static String getAppApiUrlForEnv(ENV env) {
        String retVal = "";
        switch (env) {
            case STAGE:
                retVal = testConfig.stage_base_uri();
                break;
            case PROD:
                retVal = testConfig.prod_base_uri();
                break;
            default:
                throw new IllegalArgumentException("Invalid Environment:" + env);
        }
        return retVal.endsWith("/") ? retVal.substring(0, retVal.length() - 1) : retVal;
    }


}
