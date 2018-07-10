
package net.heronation.zeyo.rest.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandLine {
	public static void Sync_file()

	{

		try

		{

			Runtime rt = Runtime.getRuntime();

			Process proc = rt.exec("docker cp D:\\TEST_SERVER_ROOT\\zeyo_image\\. 3638ea0c3ecd:/home/zeyo/zeyo_image");

			InputStream stderr = proc.getErrorStream();

			InputStreamReader isr = new InputStreamReader(stderr);

			BufferedReader br = new BufferedReader(isr);

			String line = null;

			System.out.println("Start");

			while ((line = br.readLine()) != null)

				System.out.println(line);

			System.out.println("End");

			/*----------------------------------------*/

			int exitVal = proc.waitFor();

			System.out.println("Process exitValue: " + exitVal);

		} catch (Throwable t)

		{

			t.printStackTrace();

		}

	}
	
	
	public static void Sync_excel()

	{

		try

		{

			Runtime rt = Runtime.getRuntime();

			Process proc = rt.exec("docker cp D:\\TEST_SERVER_ROOT\\temp\\. 3638ea0c3ecd:/home/zeyo/temp");

			InputStream stderr = proc.getErrorStream();

			InputStreamReader isr = new InputStreamReader(stderr);

			BufferedReader br = new BufferedReader(isr);

			String line = null;

			System.out.println("Start");

			while ((line = br.readLine()) != null)

				System.out.println(line);

			System.out.println("End");

			/*----------------------------------------*/

			int exitVal = proc.waitFor();

			System.out.println("Process exitValue: " + exitVal);

		} catch (Throwable t)

		{

			t.printStackTrace();

		}

	}
}
