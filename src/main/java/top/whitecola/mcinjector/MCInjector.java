package top.whitecola.mcinjector;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.File;

public class MCInjector {
    public static void main(String[] args) throws Throwable{
        File client = new File("./client.jar");
        if(!client.exists()){
            System.out.println("没有找到 Client.jar!");
        }


        VirtualMachineDescriptor vm = VirtualMachine.list().stream().filter(m -> m.displayName().startsWith("net.minecraft.launchwrapper.Launch")).findFirst().orElse(null);
        if(vm==null){
            System.out.println("没有找到 Minecraft 进程!");
            return;
        }

        VirtualMachine attach = VirtualMachine.attach(vm);
        attach.loadAgent(client.getAbsolutePath());
        attach.detach();
        System.out.println("完成注入");

    }


}
