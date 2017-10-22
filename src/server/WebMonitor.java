package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import server.MP3Player;


public class WebMonitor {

	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException, IOException, InterruptedException {
		
		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"/src/log.txt",true);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
		StringBuffer sb2 = new StringBuffer();
		String m = null;
		int a=0,b=0;

		for(int i=1; i<=10000; i++){	
			BufferedReader buffer2 = createStream();	
			while((m=buffer2.readLine())!=null){
				b=m.length();
				sb2.append(m);
			}
			String log = "ˢ�´�����"+i+",ԭ��ҳ��С��"+a+",��ҳ�䶯�ֽڴ�С��"+(a-b);
			writer.write(log);
			writer.newLine();
			writer.flush();
			System.out.println(log);
			if(sb2.toString().contains("<span>�ո�</span>")){//ͨ���ȶ���ҳ���Ƿ��и�����������׼ȷ��			
				/**�����ҳ�����б仯���������Kiss the rain**/
				MP3Player mp3 = new MP3Player(System.getProperty("user.dir")+"/src/kiss the rain.mp3");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = df.format(new Date());
				System.out.println(time);// new Date()Ϊ��ȡ��ǰϵͳʱ��
				writer.write(time);
				writer.newLine();
				writer.write("---------------------------------------------------------");
				writer.newLine();
				writer.flush();
				mp3.play();			
				break;
			}
			a=b;
			Thread.sleep(10000);			
			buffer2.close();
		}
		writer.close();
		fos.close();
	}
	public static BufferedReader createStream() throws MalformedURLException, IOException, UnsupportedEncodingException {
		/**��ȡ�ⲿ�����ļ�**/
		File path = new File(System.getProperty("user.dir")+"/src/path.txt");
		FileInputStream fin = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(fin,"utf-8"));
		String str=br.readLine();
		fin.close();
		br.close();
		/**����֪��������ҳ**/
		URL url = new URL(str);
		
		HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
		urlcon.setRequestProperty("contentType", "utf-8");
		urlcon.setConnectTimeout(60000);
		urlcon.setReadTimeout(60000);
		urlcon.connect();
		/**��ȡ��������ֽ�ת�ַ�����**/
		InputStream is = urlcon.getInputStream();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(is,"utf-8"));//ָ������ı���
		return buffer;
	}
		
		

}
