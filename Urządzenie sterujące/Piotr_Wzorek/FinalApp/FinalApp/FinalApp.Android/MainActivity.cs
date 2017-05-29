using System;
using Android.App;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Xamarin.Forms;

namespace FinalApp.Droid
{
	[Activity (Label = "FinalApp", Icon = "@drawable/icon", Theme="@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
	public class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity
	{
		protected override void OnCreate (Bundle bundle)
		{
			TabLayoutResource = Resource.Layout.Tabbar;
			ToolbarResource = Resource.Layout.Toolbar; 

			base.OnCreate (bundle);

			global::Xamarin.Forms.Forms.Init (this, bundle);
			LoadApplication (new FinalApp.App ());
		}

        CheckConnectivity();
        public void CheckConnectivity()
        {
            var IsConnected = CrossConnectivity.Current.IsConnected;
            if (IsConnected == true)
            {
                ConnectivityLabel.Text = "You are connected";
            }
            else
            {
                ConnectivityLabel.Text = "You are not connected";
            }
        }
	}
}

